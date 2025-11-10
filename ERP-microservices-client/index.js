import express from "express";
import mongoose from "mongoose";
import dotenv from "dotenv";
import cors from "cors";
import clientRoutes from "./src/routes/ClientRoutes.js";
import { connectDB } from "./src/config/db.js";
import { connectRabbitMQ } from "./src/config/rabbitmq.js";
import { Eureka } from "eureka-js-client";
import { loadSpringConfig } from "./src/config/config-client.js";

await loadSpringConfig("client-server"); 
dotenv.config();

const app = express();
const port = process.env.SERVER_PORT || 8026;

// MongoDB
mongoose
  .connect(process.env.MONGO_URI || "mongodb://admin:admin123@localhost:27017/gestion_client?authSource=admin")
  .then(() => console.log("MongoDB connecté ✅"))
  .catch((err) => console.error("Erreur MongoDB :", err.message));

// Middleware & Routes
//app.use(cors());
app.use(express.json());
connectDB();
app.use("/api/clients", clientRoutes);

app.get("/", (req, res) => {
  res.send("🚀 Gestion Client API fonctionne !");
});

app.listen(port, () => {
  console.log(`✅ Serveur Node actif sur http://localhost:${port}`);
});

connectRabbitMQ();

// Eureka
const eurekaClient = new Eureka({
  instance: {
    app: "CLIENT-SERVICE",
    hostName: "localhost",
    ipAddr: "localhost",
    port: { "$": port, "@enabled": true },
    vipAddress: "CLIENT-SERVICE",
    dataCenterInfo: { name: "MyOwn", "@class": "com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo" }
  },
  eureka: {
    host: "localhost",
    port: 8761,
    servicePath: "/eureka/apps/"
  }
});

eurekaClient.start(() => console.log("📡 Node enregistré dans Eureka ✅"));
