import express from "express";
import mongoose from "mongoose";
import dotenv from "dotenv";
import cors from "cors";
import clientRoutes from "./src/routes/ClientRoutes.js";
import { connectDB } from "./src/config/db.js";
import { connectRabbitMQ } from "./src/config/rabbitmq.js";

dotenv.config();

const app = express();
const port = process.env.PORT || 3000;

// 🔌 Connexion à MongoDB (Docker ou local)
mongoose
  .connect(
    process.env.MONGO_URI ||
      "mongodb://admin:admin123@localhost:27017/gestion_client?authSource=admin"
  )
  .then(() => console.log("MongoDB connecté avec succès"))
  .catch((err) => console.error("Erreur MongoDB :", err.message));

// Middleware
app.use(cors());
app.use(express.json());

connectDB();


// Routes
app.use("/api/clients", clientRoutes);

// Test route
app.get("/", (req, res) => {
  res.send("🚀 Gestion Client API fonctionne !");
});

// Lancement serveur
app.listen(port, () => {
  console.log(` Serveur en cours sur http://localhost:${port}`);
});


// Connexion à RabbitMQ
connectRabbitMQ();