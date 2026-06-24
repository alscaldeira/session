# Session

Serviço leve e seguro responsável pelo gerenciamento de sessões, controle de login e proteção de rotas para usuários autenticados.

## Como funciona

O serviço é focado em duas responsabilidades principais:
1. Autenticação (Login): Recebe as credenciais, valida no banco de dados e retorna um token (ex: JWT) válido.
2. Proteção de Rotas: Intercepta requisições, verifica a validade e a assinatura do token. Caso seja inválido ou ausente, retorna o erro `403 Forbidden`.

### Endpoints

- `GET /api/public`
  - 200 OK: Esta é uma página pública
- `GET /api/protected`
  - 200 OK: Esta é uma página protegida
  - 403 Forbidden: Sem permissão para acessar a rota
- `POST /api/auth/signup` body `{ "email": "endereco@mail.com", "password": "1234" }`
  - 201 Created: Usuário criado com sucesso
  - 400 Bad Request: Dados inválidos ou já existentes
- `POST /api/auth` body `{ "email": "endereco@mail.com", "password": "1234" }`
  - 200 OK: Login realizado com sucesso
  - 401 Unauthorized: Credenciais inválidas