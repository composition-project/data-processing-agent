FROM node:7-alpine

LABEL maintainer="Can Güney Aksakalli <can.gueney.aksakalli@fit.fraunhofer.de>"
LABEL description="Container for the npm test"

WORKDIR /usr/src/app

COPY . /usr/src/app
RUN npm install

VOLUME ["/data"]
CMD ["npm", "run-script", "bamboo"]
