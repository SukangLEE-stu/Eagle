helm install eagle-app ./eagleChart
sleep 3
IP=$(minikube ip)
echo "Eagle App IP: $IP"
INGRESS=$(kubectl describe services -n eagle-chart eagle-web-ingress-service)
echo "Eagle App Ingress: $INGRESS"

echo "Try forward service:"
sleep 3
bash ./config/forward_services.sh