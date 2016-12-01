#!/usr/bin/env bash

set -e

#script directory
BUILD_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

. ${BUILD_DIR}/../../common.sh

echo "Building image: dhr.thprom.ru:5000/activiti.app:$MVN_PROJECT_VERSION"

docker build -t "dhr.thprom.ru:5000/activiti.app:$MVN_PROJECT_VERSION" "$BUILD_DIR"