#!/bin/bash
set -ex

trap cleanup EXIT
cleanup() {
  # The local fs is mounted into the container and as such any files it writes will have their permissions changed.
  #   This will change the permissions back and clean up other files we don't want hanging around.
  sudo chown -R "$(whoami)":"$(whoami)" .
}

declare ID_RSA_CONTENTS_BASE64
# Assign actual credentials here
declare OSSRH_USERNAME_BASE64="base64_username"
declare OSSRH_PASSWORD_BASE64="base64_password"

# Change the location of these files based on where they are on your system
ID_RSA_CONTENTS_BASE64=$(base64 ~/.ssh/id_rsa | tr -d '\n') ;
[[ -n $ID_RSA_CONTENTS_BASE64 ]]
[[ -n $OSSRH_USERNAME_BASE64 ]]
[[ -n $OSSRH_PASSWORD_BASE64 ]]

# The local fs is mounted into the container and as such any files it writes will have their permissions changed.
#   This will change the permissions back and clean up other files we don't want hanging around.
sudo chown -R "$(whoami)":"$(whoami)" .

docker build infra/build -t rdglp-build-test && \
  docker run -it \
  --runtime=sysbox-runc \
  --volume "$PWD:/home/build-user/build/regex-directed-graph-line-parser" \
  rdglp-build-test '{"ID_RSA": "'"$ID_RSA_CONTENTS_BASE64"'", "OSSRH_USERNAME": "'"$OSSRH_USERNAME_BASE64"'", "OSSRH_PASSWORD": "'"$OSSRH_PASSWORD_BASE64"'"}'
