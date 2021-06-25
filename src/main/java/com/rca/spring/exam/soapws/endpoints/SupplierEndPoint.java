package com.rca.spring.exam.soapws.endpoints;


import com.rca.spring.exam.soapws.domains.SupplierModel;
import com.rca.spring.exam.soapws.repositories.ISupplierRepository;
import exam.spring.rca.com.divinirakiza.soapws.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;
import java.util.Optional;

@Endpoint
public class SupplierEndPoint {
    private final ISupplierRepository supplierRepository;

    @Autowired
    public SupplierEndPoint(ISupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @PayloadRoot(namespace = "com.rca.spring.exam/divinirakiza/soapws", localPart = "GetAllSuppliersRequest")
    @ResponsePayload
    public GetAllSuppliersResponse getAll(@RequestPayload GetAllSuppliersRequest request){

        List<SupplierModel> suppliers = this.supplierRepository.findAll();

        GetAllSuppliersResponse response = new GetAllSuppliersResponse();

        for (SupplierModel supplier: suppliers){
            Supplier _supplier = mapSupplier(supplier);
            response.getSupplier().add(_supplier);
        }

        return response;
    }

    @PayloadRoot(namespace = "com.rca.spring.exam/divinirakiza/soapws", localPart = "GetSupplierRequest")
    @ResponsePayload
    public GetSupplierResponse getById(@RequestPayload GetSupplierRequest request){
        Optional<SupplierModel> supplier = this.supplierRepository.findById(request.getId());

        if(!supplier.isPresent())
            return new GetSupplierResponse();


        GetSupplierResponse response = new GetSupplierResponse();

        Supplier _supplier = mapSupplier(supplier.get());

        response.setSupplier(_supplier);

        return response;
    }


    @PayloadRoot(namespace = "com.rca.spring.exam/divinirakiza/soapws", localPart = "CreateSupplierRequest")
    @ResponsePayload
    public CreateSupplierResponse create(@RequestPayload CreateSupplierRequest dto) {
        SupplierDTO supplierDTO = dto.getSupplier();

        SupplierModel supplier = new SupplierModel();
        supplier.setEmail(supplierDTO.getEmail());
        supplier.setNames(supplierDTO.getNames());
        supplier.setMobile(supplierDTO.getMobile());

        SupplierModel entity = this.supplierRepository.save(supplier);
        CreateSupplierResponse response = new CreateSupplierResponse();

        response.setSupplier(mapSupplier(entity));
        response.setSuccess(true);
        return response;
    }


    @PayloadRoot(namespace = "com.rca.spring.exam/divinirakiza/soapws", localPart = "UpdateSupplierRequest")
    @ResponsePayload
    public UpdateSupplierResponse update(@RequestPayload UpdateSupplierRequest request){
        SupplierDTO supplierDTO = request.getSupplier();

        Optional<SupplierModel> supplier = this.supplierRepository.findById());


        SupplierModel supplier = new SupplierModel();
        supplier.setEmail(supplierDTO.getEmail());
        supplier.setNames(supplierDTO.getNames());
        supplier.setMobile(supplierDTO.getMobile());

        Student student = studentRepository.save(_student);

        UpdateStudentResponse studentDTO = new UpdateStudentResponse();

        __student.setId(student.getId());

        studentDTO.setStudent(__student);

        return studentDTO;
    }

    @PayloadRoot(namespace = "com.rca.spring.exam/divinirakiza/soapws", localPart = "DeleteStudentRequest")
    @ResponsePayload
    public DeleteStudentResponse delete(@RequestPayload DeleteStudentRequest request){
        studentRepository.deleteById(request.getId());
        DeleteStudentResponse response = new DeleteStudentResponse();
        response.setMessage("Successfully deleted a message");
        return response;
    }



    private Supplier mapSupplier(SupplierModel supplierModel) {
        Supplier supplier = new Supplier();
        supplier.setId(supplierModel.getId());
        supplier.setEmail(supplierModel.getEmail());
        supplier.setNames(supplierModel.getNames());
        supplier.setMobile(supplierModel.getMobile());

        return supplier;
    }
}}