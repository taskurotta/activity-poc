#!/bin/bash
set -e

#script directory
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

. ${DIR}/common.sh


ask() {
  echo $1;
  while read ANS
  do {
     case $ANS in
       "Y"|"y" )
           break;
           ;;
       * )
           exit 1;
           ;;
     esac
  }
  done
}

do_clean_data()
{
    ask "This will remove all service data and table structures. Continue? (Y/N)"
    echo "Remove files from data/?/* directories (except jar file)..."
    for v in $(ls data/ | grep -v jar); do sudo rm -rf ./data/$v; done
    echo ">> done"
}

do_clean_docker()
{

    echo "Deleting stopped containers"
    docker-compose rm
}

f_clean_image()
{
    if [[ "$(docker images -q "$1" 2> /dev/null)" != "" ]]; then
        echo "Remove image: $1"
        docker rmi "$1"
   else
        echo "Image $1 does not exist"
    fi
}

do_clean_images()
{
    f_clean_image "dhr.thprom.ru:5000/activiti-app:$MVN_PROJECT_VERSION"
}


# $1 - file path
# $2 - log string
f_wait_log_message()
{
    while [ ! -f $1 ]
    do
      sleep 1
    done

    if [ -n "$2" ];
        then grep -q -m 1 "$2" <(tail -f -n 10000 $1)
    fi
}

do_up()
{
    docker-compose up -d
}

do_stop()
{
    docker-compose stop
}

do_build()
{
    build/activiti.app/build.sh
}

do_push()
{
    ask "Registry images having the same name and version would be replaced. Continue? (Y/N)"

    echo "Push image: dhr.thprom.ru:5000/activiti.app:$MVN_PROJECT_VERSION"
    docker push "dhr.thprom.ru:5000/activiti.app:$MVN_PROJECT_VERSION"

}

do_test()
{
    docker-compose -f docker-compose.yml -f docker-compose.test.yml up -d
}

do_logs_rm ()
{
  ask "This will delete all .log and .log.gz files. Continue? (Y/N)"
  echo "Removing all .log and .log.gz files..."
  find ${DIR}/data -name "*.log.gz" -type f -delete
  find ${DIR}/data -name "*.log" -type f -delete
}

for var in "$@"
do
    case "$var" in
        clean-data)
            do_clean_data
            ;;
        clean-docker)
            do_clean_docker
            ;;
        clean-images)
            do_clean_docker
            do_clean_images
            ;;
        clean)
            do_clean_docker
            do_clean_data
            ;;
        start)
            do_clean_docker
            do_up
        ;;
        test)
            do_clean_docker
            do_test
        ;;
        restart)
            do_stop
            do_clean_docker
            do_up
        ;;
        stop)
            do_stop
        ;;
        build)
            do_build
        ;;
        push)
            do_push
        ;;
        logsrm)
            do_logs_rm
        ;;
        *)
            echo "Usage: $0 {clean-data|clean-docker|clean-images|clean|start|restart|test|stop|build|push|logsrm}" >&2
            exit 3
        ;;

    esac
done