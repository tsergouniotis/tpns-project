# GENERIC
docker rm $(docker ps -a -q)
docker rmi `docker images --filter 'dangling=true' -q --no-trunc`

# DATABASE
docker run --net=tpns_nw -itd --name=tpnsdatabase -p 5432:5432 pzografos/tpns-database
docker build -t pzografos/tpns-database .

# APPLICATION SERVER
docker run --net=tpns_nw -itd --name=tpnsappserver --link tpnsdatabase:postgres -p 127.0.0.1:8080:8080 pzografos/tpns-appserver
docker build -t pzografos/tpns-appserver .

