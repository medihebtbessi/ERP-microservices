import axios from "axios";

export async function loadSpringConfig(appName = "client-server") {
  try {
    const url = `http://localhost:8888/${appName}/default`;
    const res = await axios.get(url);

    const sources = res.data.propertySources;

    for (const src of sources) {
      const props = src.source;
      for (const key in props) {
        process.env[key.toUpperCase().replace(/\./g, "_")] = props[key];
      }
    }

    console.log("✅ Configuration chargée depuis Spring Config Server");
  } catch (err) {
    console.error("❌ Erreur config Spring:", err.message);
  }
}
