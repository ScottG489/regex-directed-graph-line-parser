FROM ubuntu:24.04@sha256:db6914f1ac0c566f57857641e2214e3f3e453cb340cc2c890ed6c2b7b81b8a00

ARG BUILD_USER=ubuntu
ENV HOME=/home/${BUILD_USER}

RUN apt-get update && \
  DEBIAN_FRONTEND=noninteractive apt-get install -y --no-install-recommends \
  git=1:2.43.0-1ubuntu7.3 \
  openssh-client=1:9.6p1-3ubuntu13.16 \
  openjdk-17-jdk=17.0.19+10-1~24.04.2 \
  jq=1.7.1-3ubuntu0.24.04.2 \
  gnupg=2.4.4-2ubuntu17.4 \
  && apt-get clean \
  && rm -rf /var/lib/apt/lists/*

USER ${BUILD_USER}

RUN mkdir -p ${HOME}/.ssh \
  && mkdir -p ${HOME}/build

COPY known_hosts ${HOME}/.ssh/known_hosts
COPY run.sh ${HOME}/build/run.sh
COPY build_functions.sh ${HOME}/build/build_functions.sh

WORKDIR ${HOME}/build
ENTRYPOINT ["./run.sh"]
