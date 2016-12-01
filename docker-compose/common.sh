#!/usr/bin/env bash

# script directory
COMMON_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

# project version
export MVN_PROJECT_VERSION=$(cd ${COMMON_DIR}/.. && cat pom.xml| grep -m 1 \<version\> \
    |sed -e 's/<version>\(.*\)<\/version>/\1/'| sed -e 's/^[ \t]*//')
