FROM node:10.13-alpine
ENV NODE_ENV production
COPY ["package.json", "package-lock.json*", "./"]
RUN npm install --production --silent 
COPY . .
EXPOSE 5000
RUN npm run build
RUN npm install -g serve
CMD serve -s build