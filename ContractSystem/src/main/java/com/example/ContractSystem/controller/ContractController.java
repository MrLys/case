package com.example.ContractSystem.controller;

import com.example.ContractSystem.model.Costumer;
import com.example.ContractSystem.service.ContractService;
import com.example.ContractSystem.model.Contract;
import com.example.ContractSystem.service.MailService;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;

@RestController
@Path("/api/contract")
public class ContractController {

    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/new")
    public Response newContract(String contractAsString){
        /** Does not do any parameter validation and is thus potentially subject to malicious intent.*/
        Gson gson = new Gson();
        Contract contract = gson.fromJson(contractAsString,Contract.class);
        if (contract == null || contract.getContractNumber() == 0 || !(contract.getName() != null && !contract.getName().isEmpty())) {
            /** Not a complete contract*/
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Costumer costumer = ContractService.newCostumer(contract.getCostumerNumber());
        contract = ContractService.newContract(contract.getName(), costumer);
        String status = MailService.sendContractToCostumer(contract ,costumer);
        String contractStatus = ContractService.updateContract(contract,costumer);

        return Response.ok().entity(gson.toJson(contract)).build();
    }


}
