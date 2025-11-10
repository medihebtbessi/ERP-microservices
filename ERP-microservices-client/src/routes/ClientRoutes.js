import express from "express";
import {
  createClient,
  getClients,
  getClientById,
  updateClient,
  deleteClient
} from "../controllers/ClientController.js";

const router = express.Router();

router.post("/", createClient);
router.get("/getAll", getClients);
router.get("/:id", getClientById);
router.put("/update/:id", updateClient);
router.delete("/delete/:id", deleteClient);

export default router;
