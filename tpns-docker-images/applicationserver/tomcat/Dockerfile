FROM java:8
MAINTAINER  Panos Zografos
EXPOSE  8080
ENTRYPOINT ["/bin/sh","/entrypoint.sh"]
CMD [""]
WORKDIR /

RUN mkdir -p /tpns

ADD entrypoint.sh /
RUN chmod a+x /entrypoint.sh

ADD tpns-article-service.jar /tpns/

