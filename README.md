# FrameWorkDesign

## Overview

FrameWorkDesign is a project designed to demonstrate automation frameworks leveraging Selenium Grid with multiple browser nodes (Chrome, Edge, Firefox) orchestrated via Docker Compose. The project includes Azure Pipeline integration for CI/CD and supports local execution through Docker.

---

## Features

- Selenium Grid setup using Docker Compose for scalable browser automation.
- Chrome, Firefox, and Edge browser nodes included.
- Azure Pipelines integration for automated builds and tests.
- Easy containerized setup—no local browser or driver installation required.

---

## Prerequisites

- [Docker](https://www.docker.com/get-started) installed on your machine.
- [Docker Compose](https://docs.docker.com/compose/) (v2 or later recommended).

---

## Getting Started

### 1. Clone the Repository

```sh
git clone https://github.com/mayur0201/FrameWorkDesign.git
cd FrameWorkDesign
```

### 2. Switch to the `azure-pipelines` Branch

```sh
git checkout azure-pipelines
```

---

## Running Selenium Grid with Docker Compose

The project provides a `docker-compose-v3.yml` file to set up Selenium Hub with Chrome, Edge, and Firefox nodes.

### **Step-by-Step Execution**

1. **Start the Selenium Grid**

   ```sh
   docker compose -f docker-compose-v3.yml up
   ```

   - To run in detached mode (in the background), add the `-d` flag:

     ```sh
     docker compose -f docker-compose-v3.yml up -d
     ```

2. **Access Selenium Grid Console**

   - Once started, open your browser and visit: [http://localhost:4444](http://localhost:4444)
   - You should see the Selenium Grid UI with all registered browser nodes.

3. **Run Your Tests**

   - Configure your test scripts to point to the Selenium Hub at `http://localhost:4444/wd/hub`.
   - Execute your automation suite as per your framework setup.

4. **Stop the Selenium Grid**

   - If running in the foreground, press `Ctrl+C` to stop.
   - Then, bring down the containers and clean up the network:

     ```sh
     docker compose -f docker-compose-v3.yml down
     ```

---

## Azure Pipelines

This project is configured for use with Azure Pipelines. The pipeline definition (typically in `azure-pipelines.yml`) can be used to automate builds and test runs in your CI/CD environment.

---

## File Structure (Important Files)

- `docker-compose-v3.yml` — Docker Compose file to spin up Selenium Grid.
- `azure-pipelines.yml` — Azure Pipelines configuration.
- `src/` — Project source code and test scripts.

---

## Troubleshooting

- Make sure Docker and Docker Compose are installed and running.
- Ensure no other services are using ports `4442`, `4443`, or `4444`.
- Refer to the logs from Docker Compose for debugging node startup issues.

---

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request.

---
