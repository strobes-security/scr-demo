# Semgrep Runner in Docker

This guide explains how to set up and run Semgrep using a Docker container. The containerized approach ensures that Semgrep runs in a consistent and isolated environment without affecting your local system.

## **Prerequisites**
Ensure you have the following installed:
- **Docker** (https://docs.docker.com/get-docker/)
- **Unix Shell** (Linux/macOS Terminal or Git Bash on Windows)
- **dos2unix** (Required only for Windows users to fix line endings)

---

## **1. Create a Semgrep Execution Script**
Create a script named **`run_semgrep.sh`** with the following content:

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
What This Script Does:

Validates that a file or directory path is provided.
Runs Semgrep with the default auto ruleset.
Outputs results to semgrep_results.json.
Ensures structured execution for repeated scanning.
2. Create a Dockerfile
Create a file named Dockerfile with the following content:

dockerfile
Copy
Edit
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
What This Dockerfile Does:

Uses the latest official Semgrep image.
Copies the script into the container.
Ensures the script is executable.
Sets the script as the container's entry point.
3. Build the Docker Image
Run the following command to build the Docker image:

bash
Copy
Edit
docker build -t semgrep-runner .
What This Command Does:

Builds a new Docker image named semgrep-runner.
Prepares an isolated execution environment for Semgrep.
4. Ensure Unix Line Endings (Windows Users Only)
If you're using Windows, convert the script to Unix format to avoid execution errors:

bash
Copy
Edit
dos2unix run_semgrep.sh
Why This is Needed:

Windows uses CRLF (\r\n) line endings, which can cause execution issues in Linux-based containers.
dos2unix converts it to LF (\n), ensuring proper script execution.
5. Run Semgrep Using Docker
Now, execute Semgrep by running the container with a mounted volume:

bash
Copy
Edit
docker run --rm -v "$(pwd):/app" semgrep-runner /app/vulnerable-java-application-main
What This Command Does:

Runs the semgrep-runner container.
Mounts the current directory ($(pwd)) to /app inside the container.
Scans the directory /app/vulnerable-java-application-main using Semgrep.
6. Check the Scan Results
After execution, check the results:

bash
Copy
Edit
cat semgrep_results.json
What This Command Does:

Displays the JSON output of Semgrep findings.
Summary of Commands
Step	Command	Description
Build Docker Image	docker build -t semgrep-runner .	Creates the Semgrep container.
Convert Line Endings (Windows Only)	dos2unix run_semgrep.sh	Fixes script execution issues.
Run Semgrep	docker run --rm -v "$(pwd):/app" semgrep-runner /app/vulnerable-java-application-main	Scans the given directory.
Check Results	cat semgrep_results.json	Displays scan results.
Next Steps
Modify run_semgrep.sh to scan custom directories.
Adjust the Dockerfile to include additional dependencies if needed.
Explore Semgrep’s advanced configuration: https://semgrep.dev/docs/
