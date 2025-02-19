# Semgrep Runner in Docker

This guide sets up and runs Semgrep in a Docker container for isolated and consistent security scanning.

## **Prerequisites**
- **Docker** ([Install](https://docs.docker.com/get-docker/))
- **Unix Shell** (Linux/macOS Terminal or Git Bash on Windows)
- **dos2unix** (Windows users only)

---

## **1. Create the Semgrep Script**
Create a file named **`run_semgrep.sh`** and add the following content:

\`\`\`bash
#!/bin/bash
if [ "$#" -lt 1 ]; then
    echo "Usage: ./run_semgrep.sh <file_path>"
    exit 1
fi
FILE_PATH=$1
OUTPUT_FILE="semgrep_results.json"
echo "Running Semgrep on $FILE_PATH..."
semgrep --config=auto --output=$OUTPUT_FILE --json $FILE_PATH
echo "Scan complete. Results saved in $OUTPUT_FILE"
\`\`\`

---

## **2. Create a Dockerfile**
Create a file named **`Dockerfile`** and add the following content:

\`\`\`dockerfile
FROM returntocorp/semgrep:latest
WORKDIR /app
COPY run_semgrep.sh /app/run_semgrep.sh
RUN chmod +x /app/run_semgrep.sh
ENTRYPOINT ["/app/run_semgrep.sh"]
\`\`\`

---

## **3. Build & Run Semgrep**
### **Build the Docker Image**
\`\`\`bash
docker build -t semgrep-runner .
\`\`\`

### **(Windows Only) Convert Line Endings**
\`\`\`bash
dos2unix run_semgrep.sh
\`\`\`

### **Run Semgrep**
\`\`\`bash
docker run --rm -v "$(pwd):/app" semgrep-runner /app/vulnerable-java-application-main
\`\`\`

### **Check Scan Results**
\`\`\`bash
cat semgrep_results.json
\`\`\`

---

## **Summary**
| Step | Command |
|------|---------|
| **Build Image** | \`docker build -t semgrep-runner .\` |
| **(Windows) Fix Line Endings** | \`dos2unix run_semgrep.sh\` |
| **Run Scan** | \`docker run --rm -v "$(pwd):/app" semgrep-runner /app/vulnerable-java-application-main\` |
| **Check Results** | \`cat semgrep_results.json\` |

---

## **Next Steps**
- Modify run_semgrep.sh to scan custom directories.
- Adjust the Dockerfile to include additional dependencies if needed.
- Explore Semgrep’s advanced configuration: Semgrep Documentation

