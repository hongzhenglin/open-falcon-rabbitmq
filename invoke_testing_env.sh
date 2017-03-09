#!/bin/bash

up() {
    cd ./test/docker && docker-compose -f env.yaml up -d
    cd ../mqclient && lein run
    cd ../ && ./query_influxdb.sh createdb
}

down() {
    cd ./test/docker && docker-compose -f env.yaml down
}

usage() {
    echo "$0 up"
    echo "$0 down"
}

action=$1
case $action in
    "up")
        up
        ;;
    "down")
        down
        ;;
    *)
        usage
        ;;
esac
