# it's a good idea to pin this, but for demo purposes we'll leave it as is
FROM node:latest as builder

# automatically creates the dir and sets it as the current working dir
WORKDIR /usr/src/app
# this will allow us to run vite and other tools directly
ENV PATH /usr/src/node_modules/.bin:$PATH

COPY package.json ./

# use a more specific COPY, as this will include files like `Dockerfile`, we don't really need inside our containers.
COPY . ./

RUN npm install


FROM builder as dev

EXPOSE 3000

CMD ["npm", "run", "preview"]