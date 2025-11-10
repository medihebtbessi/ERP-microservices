  package microservice.ERP.Financiere.event;

  import microservice.ERP.Financiere.model.Devis;
  import microservice.ERP.Financiere.model.Facture;
  import org.springframework.amqp.rabbit.core.RabbitTemplate;
  import org.springframework.stereotype.Service;

  import static microservice.ERP.Financiere.config.RabbitMQConfig.EXCHANGE;

  @Service
  public class FinancePublisher {

    private final RabbitTemplate rabbitTemplate;

    public FinancePublisher(RabbitTemplate rabbitTemplate) {
      this.rabbitTemplate = rabbitTemplate;
    }

    public void publishDevisCreated(Devis devis) {
      rabbitTemplate.convertAndSend(EXCHANGE, "devis.created", devis);
      System.out.println("📤 [RabbitMQ] Devis envoyé : " + devis.getReferenceDevis());
    }

    public void publishFactureCreated(Facture facture) {
      rabbitTemplate.convertAndSend(EXCHANGE, "facture.created", facture);
      System.out.println("📤 [RabbitMQ] Facture envoyée : " + facture.getReferenceFacture());
    }
  }
