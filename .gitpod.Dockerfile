# .gitpod.Dockerfile
FROM gitpod/workspace-full

# Install dependencies (example: curl, jq)
RUN sudo apt-get update && sudo apt-get install -y \
    curl jq && \
    sudo apt-get clean