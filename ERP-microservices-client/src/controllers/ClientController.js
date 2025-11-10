import Client from "../models/Client.js";
import { publishClientEvent } from "../config/rabbitmq.js";

// ➕ Créer un client
export const createClient = async (req, res) => {
  try {
    // Vérifier si l'email existe déjà
    const existingClient = await Client.findOne({ email: req.body.email });
    if (existingClient) {
      return res.status(400).json({ message: "Un client avec cet email existe déjà." });
    }

    // Créer un nouveau client
    const client = await Client.create(req.body);

    // Publier un événement RabbitMQ
    await publishClientEvent("client.created", {
      id: client._id,
      nom: client.nom,
      prenom: client.prenom,
      email: client.email,
      statut: client.statut,
    });

    res.status(201).json({
      message: "✅ Client créé avec succès",
      client,
    });
  } catch (err) {
    console.error("❌ Erreur lors de la création du client :", err.message);
    res.status(400).json({ message: err.message });
  }
};

// 📄 Lister tous les clients
export const getClients = async (req, res) => {
  try {
    console.log("📦 Récupération de tous les clients...");
    const clients = await Client.find();
    res.status(200).json(clients);
  } catch (err) {
    console.error("❌ Erreur récupération clients :", err.message);
    res.status(500).json({ message: err.message });
  }
};

// 🔍 Obtenir un client par ID
export const getClientById = async (req, res) => {
  try {
    const client = await Client.findById(req.params.id);
    if (!client) {
      return res.status(404).json({ message: "Client non trouvé" });
    }
    res.status(200).json(client);
  } catch (err) {
    console.error("❌ Erreur récupération client :", err.message);
    res.status(500).json({ message: err.message });
  }
};

// ✏️ Modifier un client
export const updateClient = async (req, res) => {
  try {
    const client = await Client.findByIdAndUpdate(req.params.id, req.body, {
      new: true,
      runValidators: true,
    });

    if (!client) {
      return res.status(404).json({ message: "Client non trouvé" });
    }

    // Publier un événement RabbitMQ
    await publishClientEvent("client.updated", {
      id: client._id,
      nom: client.nom,
      prenom: client.prenom,
      email: client.email,
      statut: client.statut,
    });

    res.status(200).json({
      message: "✏️ Client mis à jour avec succès",
      client,
    });
  } catch (err) {
    console.error("❌ Erreur mise à jour client :", err.message);
    res.status(400).json({ message: err.message });
  }
};

// 🗑️ Supprimer un client
export const deleteClient = async (req, res) => {
  try {
    const client = await Client.findByIdAndDelete(req.params.id);
    if (!client) {
      return res.status(404).json({ message: "Client non trouvé" });
    }

    // Publier un événement RabbitMQ
    await publishClientEvent("client.deleted", { id: client._id });

    res.status(200).json({
      message: "🗑️ Client supprimé avec succès",
      id: client._id,
    });
  } catch (err) {
    console.error("❌ Erreur suppression client :", err.message);
    res.status(500).json({ message: err.message });
  }
};