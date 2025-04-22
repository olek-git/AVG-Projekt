import amqp from 'amqplib';

async function startLogger() {
  const conn = await amqp.connect('amqp://rabbitmq');
  const channel = await conn.createChannel();
  const queue = 'log_queue';

  await channel.assertQueue(queue, { durable: false });

  console.log('[Logserver] Warte auf Logs...');

  channel.consume(queue, (msg) => {
    if (msg) {
      const log = JSON.parse(msg.content.toString());
      console.log(`[${log.timestamp}] [${log.service}] ${log.level}: ${log.message}`);
      channel.ack(msg);
    }
  });
}

startLogger();