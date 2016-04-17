#!/bin/sh
set -e
set -x
export DEBIAN_FRONTEND=noninteractive

cd /tpns
java $@ -jar tpns-article-service.jar  


