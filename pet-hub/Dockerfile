# Step 1: Build the Angular app using a Node.js image
FROM node:18 AS build
WORKDIR /app

# Copy package files from the correct directory
COPY package*.json ./

# Install Angular CLI globally and project dependencies
RUN npm install
RUN npm install -g @angular/cli

# Copy the entire project contents (the `pet-hub` directory itself)
COPY . .

# Build the Angular application for production
RUN ng build

# # Custom Nginx configuration for Angular routing
# COPY ./pet-hub/nginx.conf /etc/nginx/conf.d/default.conf

# # Expose port 80
EXPOSE 80

CMD ["npm", "start"]