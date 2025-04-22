# grpc-service/Dockerfile
FROM node:23-bookworm-slim
WORKDIR /app
COPY . .
RUN npm install
CMD ["npx", "ts-node", "src/index.ts"]