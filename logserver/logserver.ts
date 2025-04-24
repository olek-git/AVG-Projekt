import express from 'express';
import bodyParser from 'body-parser';

// Initialisiere den Express-Server
const app = express();
const port = 3000; // Du kannst den Port nach Bedarf ändern

// Body-Parser Middleware für JSON-Daten
app.use(bodyParser.json());

// Endpoint zum Empfangen der Logs
app.post('/log', (req, res) => {
  const log = req.body;

  // Überprüfe, ob die notwendigen Felder vorhanden sind
  if (!log.timestamp || !log.level || !log.service || !log.message) {
    return res.status(400).json({ error: 'Invalid log format' });
  }

  // Log-Nachricht ausgeben (du kannst dies auch in eine Datei oder eine Datenbank speichern)
  console.log(`[${log.timestamp}] [${log.service}] ${log.level}: ${log.message}`);

  // Antwort senden
  res.status(200).json({ message: 'Log received' });
});

// Starte den Server
app.listen(port, () => {
  console.log(`Log server running on http://localhost:${port}`);
});