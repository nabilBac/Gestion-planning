# Gestion Planning (en cours de développement)

Application Java Spring Boot permettant la gestion des agents de sécurité, des sites d'intervention, et la génération automatisée des plannings selon des règles métier (rotation, jours fixes, etc.).

⚠️ Le projet est **en cours de développement**. Seule la **partie admin** (CRUD agents, sites, planning automatique) est actuellement fonctionnelle.

---

## 🛠 Technologies utilisées

- Java 17
- Spring Boot 3
- Maven
- Thymeleaf
- MySQL
- Spring Security

---

## ✅ Fonctionnalités disponibles

- Authentification / Autorisation (admin)
- Interface d'administration :
  - CRUD des agents de sécurité
  - CRUD des sites géographiques
  - Gestion des créneaux horaires (planning)

---

## 🚧 Fonctionnalités à venir

- Génération automatique des plannings :
  - Rotation automatique des agents
  - Présence obligatoire les vendredis/samedis
  - Attribution de jours de repos en semaine

- Interface utilisateur :
  - Vue agent du planning
  - Espace personnel (planning, profils, etc.)

---

## ⚙️ Installation

### Prérequis

- Java 17
- Maven
- MySQL avec une base nommée `planningdb` (ou à adapter selon `application.properties`)

### Étapes

1. Cloner le projet :
   ```bash
   git clone https://github.com/nabilBac/Gestion-planning.git
   cd Gestion-planning
