#!/bin/bash
set -e

source /home/build-user/build/build_functions.sh

set +x
setup_credentials "$1"
set -x

declare -r _PROJECT_NAME='regex-directed-graph-line-parser'
declare -r _GIT_REPO='git@github.com:ScottG489/regex-directed-graph-line-parser.git'

git clone $_GIT_REPO
cd $_PROJECT_NAME

build_test