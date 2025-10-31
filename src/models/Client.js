import mongoose from "mongoose";

const clientSchema = new mongoose.Schema({
  nom: { type: String, required: true },
  prenom: { type: String, required: true },
  email: { type: String, required: true, unique: true },
  telephone: String,
  adresse: String,
  statut: { type: String, enum: ["ACTIF", "INACTIF"], default: "ACTIF" },
  dateCreation: { type: Date, default: Date.now }
});

export default mongoose.model("Client", clientSchema);