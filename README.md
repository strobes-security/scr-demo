# SCR

Full Command List:
Create a script file (run_semgrep.sh):

Paste the script code into a file.
Create a Dockerfile:

Copy the Dockerfile content into a file named Dockerfile.
Build Docker Image:

bash
Copy
Edit
docker build -t semgrep-runner .
Ensure Unix Line Endings (if using Windows):

bash
Copy
Edit
dos2unix run_semgrep.sh
Run Semgrep Using Docker:

bash
Copy
Edit
docker run --rm -v "$(pwd):/app" semgrep-runner /app/vulnerable-java-application-main
Check the Results: After the scan, check the semgrep_results.json file in your working directory.
