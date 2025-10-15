FROM python:3.11-slim

# Set working directory
WORKDIR /app

# Copy dependency file
COPY python-libs.txt .

# Install dependencies
RUN pip install --no-cache-dir -r python-libs.txt

# Copy your app code
COPY . .

# Expose your service port (example: 6001)
EXPOSE 6001

# Run the app
CMD ["python", "listing_service.py", "--port=6001", "--debug=true"]