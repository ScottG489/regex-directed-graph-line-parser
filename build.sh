#!/usr/bin/env bash

readonly IMAGE_NAME='scottg489/regex-directed-graph-line-parser-build:latest'
readonly ID_RSA=$1

read -r -d '' JSON_BODY <<- EOM
  {
  "ID_RSA": "$ID_RSA"
  }
EOM

curl -v -sS -w '\n%{http_code}' \
  --data-binary "$JSON_BODY" \
  "https://api.conjob.io/job/run?image=$IMAGE_NAME" \
  | tee /tmp/foo \
  | sed '$d' && \
  [ "$(tail -1 /tmp/foo)" -eq 200 ]
