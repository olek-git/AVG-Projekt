import express from 'express';

const app = express();
const port = 3000;

// Jetzt JSON akzeptieren
app.use(express.json());

app.post('/log', (req, res) => {
  const log = req.body;
  console.log(`[${new Date(log.timestamp).toLocaleString()}] ${log.level} - ${log.logger}: ${log.message}`);
  res.sendStatus(200);
});

app.listen(port, () => {
  console.log(`🚀 Logserver läuft auf http://localhost:${port}`);
});