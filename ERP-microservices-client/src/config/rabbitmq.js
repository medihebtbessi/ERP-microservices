import amqplib from "amqplib";
import crypto from "crypto";

let channel;

export const connectRabbitMQ = async () => {
  try {
    const connection = await amqplib.connect("amqp://erp:erp@localhost:5672");
    channel = await connection.createChannel();

    const exchange = "client.events";
    const queue = "client.test.q";
    const routingKey = "client.*";

    // ✅ Exchange durable
    await channel.assertExchange(exchange, "topic", { durable: true });

    // ✅ Queue durable, non supprimée automatiquement
    await channel.assertQueue(queue, {
      durable: true,
      autoDelete: false, // empêche la suppression automatique
      exclusive: false,  // permet la persistance
    });

    await channel.bindQueue(queue, exchange, routingKey);

    console.log(`
✅ Connecté à RabbitMQ :
   Exchange : ${exchange}
   Queue    : ${queue}
   Binding  : ${routingKey}
    `);

    connection.on("close", () => {
      console.log("⚠️ Connexion RabbitMQ fermée !");
    });

    connection.on("error", (err) => {
      console.error("❌ Erreur RabbitMQ :", err.message);
    });
  } catch (error) {
    console.error("❌ Erreur de connexion RabbitMQ :", error.message);
  }
};

export const publishClientEvent = async (type, data) => {
  if (!channel) {
    console.error("⚠️ Channel RabbitMQ non initialisé !");
    return;
  }

  const event = {
    eventId: crypto.randomUUID(),
    type,
    source: "client-service",
    timestamp: new Date().toISOString(),
    data,
  };


  // ✅ Message persistant
  channel.publish("client.events", type, Buffer.from(JSON.stringify(event)), {
    persistent: true,
  });

  console.log(`📤 Événement publié : ${type}`, event);
};
