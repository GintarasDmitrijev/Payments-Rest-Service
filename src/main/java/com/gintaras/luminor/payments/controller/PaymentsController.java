package com.gintaras.luminor.payments.controller;

import com.gintaras.luminor.payments.exception.PaymentNotFoundException;
import com.gintaras.luminor.payments.model.AbstractPaymentType;
import com.gintaras.luminor.payments.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Controller that serves requests from UI.
 *
 * @author gintaras
 */
@RestController
@RequestMapping(value = "/payments")
public class PaymentsController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public CollectionModel<EntityModel<AbstractPaymentType>> all() {

        List<EntityModel<AbstractPaymentType>> payments = paymentService.findAll().stream()
                .map(payment -> EntityModel.of(payment,
                        linkTo(methodOn(PaymentsController.class).one(payment.getId())).withSelfRel(),
                        linkTo(methodOn(PaymentsController.class).all()).withRel("payments")))
                .collect(Collectors.toList());

        return CollectionModel.of(payments, linkTo(methodOn(PaymentsController.class).all()).withSelfRel());

    }

    @GetMapping("/{id}")
    EntityModel<AbstractPaymentType> one(@PathVariable Long id) {

        AbstractPaymentType payment = paymentService.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException(id));

        return EntityModel.of(payment,
                linkTo(methodOn(PaymentsController.class).one(id)).withSelfRel(),
                linkTo(methodOn(PaymentsController.class).all()).withRel("payments"));
    }

    @PutMapping("/{id}/cancel")
    EntityModel<AbstractPaymentType> cancel(@PathVariable Long id) {

        AbstractPaymentType payment = paymentService.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException(id));

        paymentService.validatePaymentForCancellation(payment);
        payment = paymentService.cancelPayment(payment);

        return EntityModel.of(payment,
                linkTo(methodOn(PaymentsController.class).cancel(id)).withSelfRel(),
                linkTo(methodOn(PaymentsController.class).one(id)).withRel("one"),
                linkTo(methodOn(PaymentsController.class).all()).withRel("payments"));
    }

}

