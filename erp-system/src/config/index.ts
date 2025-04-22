// src/index.ts
import * as grpc from '@grpc/grpc-js';
import * as protoLoader from '@grpc/proto-loader';
import { log } from './logger';

const PROTO_PATH = '../shared/service.proto';

const packageDefinition = protoLoader.loadSync(PROTO_PATH);
const proto = grpc.loadPackageDefinition(packageDefinition) as any;

function GetProductList(_: any, callback: any) {
  log('Produkte werden geladen');
  const products = [
    { id: '1', name: 'Sneaker', price: 79.99 },
    { id: '2', name: 'Hoodie', price: 49.99 },
  ];
  callback(null, { products });
}

function CreateOrder(call: any, callback: any) {
  log(`Bestellung erhalten: ${JSON.stringify(call.request)}`);
  const response = { order_id: 'abc123', status: 'confirmed' };
  callback(null, response);
}

function main() {
  const server = new grpc.Server();
  server.addService(proto.ShopGateway.service, {
    GetProductList,
    CreateOrder,
  });
  server.bindAsync('0.0.0.0:50051', grpc.ServerCredentials.createInsecure(), () => {
    log('gRPC Server läuft auf Port 50051');
    server.start();
  });
}

main();