FROM ubuntu:latest
LABEL authors="alvarjaxell"

ENTRYPOINT ["top", "-b"]