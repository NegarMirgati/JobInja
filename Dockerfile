FROM node:10.13-alpine
ENV NODE_ENV production
WORKDIR /react-client
COPY ["react-client/package.json", "react-client/package-lock.json*", "./"]
RUN npm install --production --silent 
COPY react-client/ .
EXPOSE 3000
CMD npm start