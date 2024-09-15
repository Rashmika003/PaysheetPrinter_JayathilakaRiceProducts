"use client"

import { Button } from "@/components/ui/button"
import {
    Dialog,
    DialogClose,
    DialogContent,
    DialogHeader,
    DialogTitle,
    DialogTrigger,
} from "@/components/ui/dialog"
import DeleteEmployee from "./DeleteEmployee"
import { useEffect, useState } from "react";
import { useToast } from "@/hooks/use-toast";

interface dataDataType {
    name: string
    position: string
    monthly_Salary: number
    bonus: number
    advance_payments: number
    loan_to_pay: number
    loan_payment_for_month: number
    worked_days_count: number
    should_work_dates_total: number
    calculated_salary: number
    id: number
    etf: number
}

interface ChildProps {
    parentData: dataDataType;
}


const EmployeeOptions: React.FC<ChildProps> = ({ parentData}) => {

    {/* https://ui.shadcn.com/docs/components/toast */ }
    const { toast } = useToast()

    const [id, setId] = useState(0);
    const [name, setName] = useState("");
    const [position, setPosition] = useState('');
    const [monthlySalary, setMonthlySalary] = useState(0);
    const [etf, setEtf] = useState(0);
    const [bonus, setBonus] = useState(0);
    const [advancePayments, setAdvancePayments] = useState(0);
    const [loanToPay, setLoanToPay] = useState(0);
    const [loanPaymentForMonth, setLoanPaymentForMonth] = useState(0);
    const [workedDaysCount, setWorkedDaysCount] = useState(0);
    const [shouldWorkDatesTotal, setShouldWorkDatesTotal] = useState(0);
    const [calculatedSalary, setCalculatedSalary] = useState(0);



    // form submit
    const handleFormSubmit = async (formData: FormData) => {

        //buttonIsClicked function update useState to track whether 
        //form is submitted or not. action method cannot use for such things

        try {
            console.log("request sending to nextjs API : updateEmployee");

            const res = await fetch('http://localhost:3000/api/update-employee', {
                method: 'POST',
                body: formData
            })

            const responseData = await res.json()
            console.log(responseData)

            if (res.ok) {
                toast({
                    description: "Employee Updated Successfully",
                })
                console.log("Success!!!")
            }
            else {
                toast({
                    variant: "destructive",
                    title: "Oh! Something went wrong!",
                    description: "There was a problem with your request. Please Check the internet Connection",
                })
            }
        }
        catch (error) {
            console.error('Error submitting form:', error);
            toast({
                variant: "destructive",
                title: "Oh! Something Unexpected Happend.",
                description: "There was a problem with your request. Please Check the internet Connection",
            })
        } finally {

        }

    }

    useEffect(() => {
        setId(parentData.id)
        setName(parentData.name);
        setPosition(parentData.position);
        setMonthlySalary(parentData.monthly_Salary);
        setEtf(parentData.etf);
        setBonus(parentData.bonus);
        setAdvancePayments(parentData.advance_payments);
        setLoanToPay(parentData.loan_to_pay);
        setLoanPaymentForMonth(parentData.loan_payment_for_month);
        setWorkedDaysCount(parentData.worked_days_count);
        setShouldWorkDatesTotal(parentData.should_work_dates_total);
        setCalculatedSalary(parentData.calculated_salary)
    }, [parentData]);


    return (
        <>
            <div className="flex justify-center content-center">
                <Dialog>
                    <DialogTrigger asChild>
                        {/* <Button variant="outline" className="bg-blue-500 w-full">Print</Button> */}
                        <button className="bg-blue-500 my-1 w-24 rounded-lg hover:bg-blue-600">Options</button>
                    </DialogTrigger>
                    <DialogContent className="bg-gray-100 max-h-lvh overflow-auto overscroll-contain">
                        <DialogHeader>
                            <DialogTitle className="text-black">All Options For Employee</DialogTitle>
                        </DialogHeader>
                        <div className="flex flex-col justify-center items-center">

                            <form action={handleFormSubmit} className="max-w-full rounded-lg">

                                <div className="space-y-1">

                                    <div className="flex items-center sr-only">
                                        <label className="w-1/3 text-right mr-4 text-gray-700">Id:</label>
                                        <input
                                            type="text"
                                            name="id"
                                            value={id}
                                            onChange={(e) => setId(Number(e.target.value))}
                                            className="w-2/3 px-3 py-1 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                                        />
                                    </div>

                                    <div className="flex items-center">
                                        <label className="w-1/3 text-right mr-4 text-gray-700">Name:</label>
                                        <input
                                            type="text"
                                            name="name"
                                            value={name}
                                            onChange={(e) => setName(e.target.value)}
                                            className="w-2/3 px-3 py-1 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                                        />
                                    </div>

                                    <div className="flex items-center">
                                        <label className="w-1/3 text-right mr-4 text-gray-700">Position:</label>
                                        <input
                                            type="text"
                                            name="position"
                                            value={position}
                                            onChange={(e) => setPosition(e.target.value)}
                                            className="w-2/3 px-3 py-1 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                                        />
                                    </div>

                                    <div className="flex items-center">
                                        <label className="w-1/3 text-right mr-4 text-gray-700">Monthly Salary:</label>
                                        <input
                                            type="number"
                                            name="monthly_Salary"
                                            value={monthlySalary}
                                            onChange={(e) => setMonthlySalary(Number(e.target.value))}
                                            className="w-2/3 px-3 py-1 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                                        />
                                    </div>

                                    <div className="flex items-center">
                                        <label className="w-1/3 text-right mr-4 text-gray-700">ETF:</label>
                                        <input
                                            type="number"
                                            name="etf"
                                            value={etf}
                                            onChange={(e) => setEtf(Number(e.target.value))}
                                            className="w-2/3 px-3 py-1 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                                        />
                                    </div>

                                    <div className="flex items-center">
                                        <label className="w-1/3 text-right mr-4 text-gray-700">Bonus:</label>
                                        <input
                                            type="number"
                                            name="bonus"
                                            value={bonus}
                                            onChange={(e) => setBonus(Number(e.target.value))}
                                            className="w-2/3 px-3 py-1 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                                        />
                                    </div>

                                    <div className="flex items-center">
                                        <label className="w-1/3 text-right mr-4 text-gray-700">Advance Payments:</label>
                                        <input
                                            type="number"
                                            name="advance_payments"
                                            value={advancePayments}
                                            onChange={(e) => setAdvancePayments(Number(e.target.value))}
                                            className="w-2/3 px-3 py-1 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                                        />
                                    </div>

                                    <div className="flex items-center">
                                        <label className="w-1/3 text-right mr-4 text-gray-700">Loan to Pay:</label>
                                        <input
                                            type="number"
                                            name="loan_to_pay"
                                            value={loanToPay}
                                            onChange={(e) => setLoanToPay(Number(e.target.value))}
                                            className="w-2/3 px-3 py-1 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                                        />
                                    </div>

                                    <div className="flex items-center">
                                        <label className="w-1/3 text-right mr-4 text-gray-700">Loan Payment for Month:</label>
                                        <input
                                            type="number"
                                            name="loan_payment_for_month"
                                            value={loanPaymentForMonth}
                                            onChange={(e) => setLoanPaymentForMonth(Number(e.target.value))}
                                            className="w-2/3 px-3 py-1 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                                        />
                                    </div>

                                    <div className="flex items-center">
                                        <label className="w-1/3 text-right mr-4 text-gray-700">Worked Days Count:</label>
                                        <input
                                            type="number"
                                            name="worked_days_count"
                                            value={workedDaysCount}
                                            onChange={(e) => setWorkedDaysCount(Number(e.target.value))}
                                            className="w-2/3 px-3 py-1 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                                        />
                                    </div>

                                    <div className="flex items-center">
                                        <label className="w-1/3 text-right mr-4 text-gray-700">Should Work Dates Total:</label>
                                        <input
                                            type="number"
                                            name="should_work_dates_total"
                                            value={shouldWorkDatesTotal}
                                            onChange={(e) => setShouldWorkDatesTotal(Number(e.target.value))}
                                            className="w-2/3 px-3 py-1 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                                        />
                                    </div>
                                </div>

                                <div className="ml-5 text-lg font-bold">
                                    <span>Final Salary : {calculatedSalary} </span> 
                                </div>

                                <div className="my-2 text-center">
                                    <button
                                        type="submit"
                                        className="px-8 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2"
                                    >
                                        Update And Calculate Final Salary
                                    </button>
                                </div>
                            </form>



                            {/* buttons */}
                            <div className="flex flex-col space-y-1 w-2/3 justify-center">

                                {/* delete */}
                                <div className="w-full">
                                    <DeleteEmployee parentData={parentData}/>
                                </div>


                                {/* <button type="submit" className="px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2">
                                    Delete Employee
                                </button> */}

                                <Button type="submit" size="lg" className="bg-gray-600">
                                    <span className="">Print</span>
                                </Button>
                                {/* <button type="submit" className="px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2">
                                    Print
                                </button> */}


                                {/* close the window */}
                                <DialogClose asChild>
                                    <Button type="button" variant="secondary" className="bg-gray-300 hover:bg-gray-400">
                                        Close
                                    </Button>
                                </DialogClose>
                            </div>
                        </div>
                    </DialogContent>
                </Dialog>
            </div>
        </>
    )
}
export default EmployeeOptions
