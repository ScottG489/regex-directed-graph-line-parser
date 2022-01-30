#!/bin/bash
set -e

get_git_root_dir() {
  echo -n "$(git rev-parse --show-toplevel)"
}

setup_credentials() {
  set +x
  local ID_RSA_CONTENTS

  readonly ID_RSA_CONTENTS=$(echo -n "$1" | jq -r .ID_RSA | base64 --decode)
  [[ -n $ID_RSA_CONTENTS ]]

  printf -- "$ID_RSA_CONTENTS" >/home/build-user/.ssh/id_rsa

  chmod 400 /home/build-user/.ssh/id_rsa
}

build_test() {
  local ROOT_DIR
  readonly ROOT_DIR=$(get_git_root_dir)
  cd "$ROOT_DIR"

  ./gradlew --info build
}