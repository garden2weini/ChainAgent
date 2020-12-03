FROM ubuntu:16.04

MAINTAINER Merlin

USER root
WORKDIR /root

RUN bash
RUN sed -i s@/archive.ubuntu.com/@/mirrors.aliyun.com/@g /etc/apt/sources.list
RUN apt-get clean
RUN apt-get update

RUN apt-get install -y openssl curl
RUN cd ~ && mkdir -p fisco && cd fisco
RUN curl -#LO https://github.com/FISCO-BCOS/FISCO-BCOS/releases/download/v2.6.0/build_chain.sh && chmod u+x build_chain.sh
RUN bash build_chain.sh -l 127.0.0.1:4 -p 30300,20200,8545

RUN apt-get install -y default-jdk
RUN cd ~/fisco &&  curl -#LO https://github.com/FISCO-BCOS/console/releases/download/v2.7.0/download_console.sh && bash download_console.sh
RUN cp -n console/conf/config-example.toml console/conf/config.toml
RUN cp ../nodes/127.0.0.1/sdk/* console/conf/
# Start console
# cd ~/fisco/console && bash start.sh

#CMD
#ENTRYPOINT /bin/bash
ENTRYPOINT bash nodes/127.0.0.1/start_all.sh

# docker build -t merlin/bcos2:v1 .
# docker run -dit --name bcos2 -v ~/nodedata:/root -p 35500:35500 -p 53300:53300 merlin/bcos2:v1
# docker run -dit --name bcos2 -v ~/nodedata:/nodedata -p 35500:35500 -p 53300:53300 merlin/bcos2:v1
# docker ps -a
# docker exec -it $(docker ps -a | grep bcos2 | awk 'NR==1{print $1}') bash
# ps -ef | grep -v grep | grep fisco-bcos