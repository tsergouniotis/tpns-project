#!/bin/bash
set -e

echo "******CREATING TPNS DATABASE******"

# TPNS DB START
TEST=`gosu postgres postgres --single <<- EOSQL
   SELECT 1 FROM pg_database WHERE datname='$TPNS_DBNAME';
EOSQL`

if [[ $TEST == "1" ]]; then
    echo "TPNS Database already exists"
else
echo "Creating TPNS user..."
gosu postgres postgres --single <<- EOSQL
   CREATE ROLE $TPNS_USER WITH LOGIN ENCRYPTED PASSWORD '${TPNS_PASS}' CREATEDB;
EOSQL
echo "Creating TPNS database..."
gosu postgres postgres --single <<- EOSQL
   CREATE DATABASE $TPNS_DBNAME WITH OWNER $TPNS_USER TEMPLATE template0 ENCODING 'UTF8';
EOSQL
echo "Assigning TPNS database to TPNS User"
gosu postgres postgres --single <<- EOSQL
   GRANT ALL PRIVILEGES ON DATABASE $TPNS_DBNAME TO $TPNS_USER;
EOSQL
fi

echo ""
echo "******TPNS DATABASE CREATED******"
# TPNS DB END

exec "$@"
