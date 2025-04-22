// src/logger.ts
import axios from 'axios';

const logserverUrl = process.env.LOGSERVER_URL || 'http://localhost:5000/log';

export async function log(message: string, level = 'INFO', service = 'grpc-service') {
  try {
    await axios.post(logserverUrl, {
      timestamp: new Date().toISOString(),
      service,
      level,
      message
    });
  } catch (err) {
    console.error('Fehler beim Logging:', err.message);
  }
}