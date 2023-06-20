// Criar usuário admin com a senha
db.createUser({
    user: 'admin',
    pwd: 'mm236533',
    roles: [{ role: 'readWrite', db: 'luizalabs' }]
  });
  
  // Usar a base de dados luizalabs
  db = db.getSiblingDB('luizalabs');
  
  // Criar a coleção wishlist
  db.createCollection('wishlist');