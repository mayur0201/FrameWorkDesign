#!/bin/bash
cd "/Users/Mayur Mangal/IdeaProjects/FrameworkDesign" || { echo "Directory change failed"; exit 1; }
exec docker-compose -p selenium-grid down
