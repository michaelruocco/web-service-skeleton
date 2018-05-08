FROM alpine:edge

RUN \
apk add --no-cache mongodb-tools

COPY init.json /init.json

ENV MONGO_HOST localhost
ENV MONGO_PORT 27017
ENV MONGO_DATABASE skeleton_db

CMD mongoimport --host $MONGO_HOST --db $MONGO_DATABASE --collection mongoCustomer --type json --file /init.json --jsonArray