# Use the official Semgrep image as the base
FROM returntocorp/semgrep:latest

# Set the working directory in the container
WORKDIR /app

# Copy custom Semgrep rules into the image
COPY rules/ /app/rules/

# Copy the Semgrep execution script
COPY run_semgrep.sh /app/run_semgrep.sh

# Ensure the script is executable
RUN chmod +x /app/run_semgrep.sh

# Set the entry point to the script
ENTRYPOINT ["/app/run_semgrep.sh"]