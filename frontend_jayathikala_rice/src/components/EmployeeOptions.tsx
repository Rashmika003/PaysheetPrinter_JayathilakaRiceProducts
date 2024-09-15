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
    onChildDataChange: (newChildData: string) => void;
}


const EmployeeOptions: React.FC<ChildProps> = ({ parentData, onChildDataChange, }) => {

    const [name, setName] = useState("")
    const [position, setPosition] = useState('');
    const [monthlySalary, setMonthlySalary] = useState(0.0);
    const [etf, setEtf] = useState(0.0);
    const [bonus, setBonus] = useState(0.0);
    const [advancePayments, setAdvancePayments] = useState(0.0);
    const [loanToPay, setLoanToPay] = useState(0.0);
    const [loanPaymentForMonth, setLoanPaymentForMonth] = useState(0.0);
    const [workedDaysCount, setWorkedDaysCount] = useState(0.0);
    const [shouldWorkDatesTotal, setShouldWorkDatesTotal] = useState(0.0);

    // use this to call parent class function 
    const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        onChildDataChange("some text"); // this call the parent component function
    };

    useEffect(() => {

        setName(parentData.name)
        //todo:assigne the the rest...

    }, []);


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

                            <form className="max-w-full rounded-lg">

                                <div className="space-y-1">
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
                                        <input type="text" name="position" className="w-2/3 px-3 py-1 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
                                    </div>

                                    <div className="flex items-center">
                                        <label className="w-1/3 text-right mr-4 text-gray-700">Monthly Salary:</label>
                                        <input type="number" name="monthly_Salary" className="w-2/3 px-3 py-1 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
                                    </div>

                                    <div className="flex items-center">
                                        <label className="w-1/3 text-right mr-4 text-gray-700">ETF:</label>
                                        <input type="number" name="ETF" className="w-2/3 px-3 py-1 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
                                    </div>

                                    <div className="flex items-center">
                                        <label className="w-1/3 text-right mr-4 text-gray-700">Bonus:</label>
                                        <input type="number" name="bonus" className="w-2/3 px-3 py-1 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
                                    </div>

                                    <div className="flex items-center">
                                        <label className="w-1/3 text-right mr-4 text-gray-700">Advance Payments:</label>
                                        <input type="number" name="advance_payments" className="w-2/3 px-3 py-1 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
                                    </div>

                                    <div className="flex items-center">
                                        <label className="w-1/3 text-right mr-4 text-gray-700">Loan to Pay:</label>
                                        <input type="number" name="loan_to_pay" className="w-2/3 px-3 py-1 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
                                    </div>

                                    <div className="flex items-center">
                                        <label className="w-1/3 text-right mr-4 text-gray-700">Loan Payment for Month:</label>
                                        <input type="number" name="loan_payment_for_month" className="w-2/3 px-3 py-1 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
                                    </div>

                                    <div className="flex items-center">
                                        <label className="w-1/3 text-right mr-4 text-gray-700">Worked Days Count:</label>
                                        <input type="number" name="worked_days_count" className="w-2/3 px-3 py-1 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
                                    </div>

                                    <div className="flex items-center">
                                        <label className="w-1/3 text-right mr-4 text-gray-700">Should Work Dates Total:</label>
                                        <input type="number" name="should_work_dates_total" className="w-2/3 px-3 py-1 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
                                    </div>
                                </div>

                                <div className="ml-5 text-lg font-bold">
                                    <span>Final Salary :</span>
                                </div>

                                <div className="my-2 text-center">
                                    <button type="submit" className="px-8 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2">
                                        Update And Calculate Final Salary
                                    </button>
                                </div>
                            </form>



                            {/* buttons */}
                            <div className="flex flex-col space-y-1 w-2/3 justify-center">

                                {/* delete */}
                                <div className="w-full">
                                    <DeleteEmployee />
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
