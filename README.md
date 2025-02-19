# Semgrep Runner in Docker

This guide explains how to set up and run Semgrep using a Docker container. The containerized approach ensures that Semgrep runs in a consistent and isolated environment without affecting your local system.

---

## **Prerequisites**
Ensure you have the following installed:
- **Docker** ([Get Docker](https://docs.docker.com/get-docker/))
- **Unix Shell** (Linux/macOS Terminal or Git Bash on Windows)
- **dos2unix** (Required only for Windows users to fix line endings)

---

## **1. Create the Semgrep Execution Script**
Create a file named **`run_semgrep.sh`** and add the following content:

```bash
#!/bin/bash

# Validate inputs
if [ "$#" -lt 1 ]; then
    echo "Usage: ./run_semgrep.sh <file_path>"
    exit 1
fi

FILE_PATH=$1
OUTPUT_FILE="semgrep_results.json"

# Run Semgrep with default registry rules
echo "Running Semgrep on $FILE_PATH using default registry rules..."
semgrep --config=auto --output=$OUTPUT_FILE --json $FILE_PATH

# Notify user
echo "Scan complete. Results saved in $OUTPUT_FILE"

## **2.Create a Dockerfile
Create a file named Dockerfile and add the following content:

# Use the official Semgrep image
FROM returntocorp/semgrep:latest

# Set working directory
WORKDIR /app

# Copy the Semgrep execution script into the container
COPY run_semgrep.sh /app/run_semgrep.sh

# Ensure the script is executable
RUN chmod +x /app/run_semgrep.sh

# Set the default entry point
ENTRYPOINT ["/app/run_semgrep.sh"]

## **3.What This Dockerfile Does
Uses the latest official Semgrep image.
Copies the script into the container.
Ensures the script is executable.
Sets the script as the container's entry point.

## **4. Build the Docker Image
Run the following command to build the Docker image:
docker build -t semgrep-runner .
Further Ensure Unix Line Endings (Windows Users Only)
If you're using Windows, convert the script to Unix format to avoid execution errors: 
**dos2unix run_semgrep.sh**

## **5.Final Command:
docker run --rm -v "$(pwd):/app" semgrep-runner /app/vulnerable-java-application-main

Check the Scan Results at the json file created in the path.

## **6. Next Steps:
Modify run_semgrep.sh to scan custom directories with custom languages.
add more support to rules.
Adjust the Dockerfile to include additional dependencies if needed.
Explore Semgrep’s advanced configuration: Semgrep Documentation
