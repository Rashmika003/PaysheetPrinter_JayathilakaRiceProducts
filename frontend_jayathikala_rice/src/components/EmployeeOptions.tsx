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
import PrintPaySheet from "./PrintPaySheet";

interface dataDataType {
    name: string
    position: string
    monthlySalary: number
    otherAllowances: number
    advance_payments: number
    loan_to_pay: number
    loan_payment_for_month: number
    worked_days_count: number
    should_work_dates_total: number
    calculated_salary: number
    id: number
    etf: number
    specialSupports: number
    other_deductions: number
    month: number
}

interface ChildProps {
    parentData: dataDataType;
}


const EmployeeOptions: React.FC<ChildProps> = ({ parentData }) => {

    {/* https://ui.shadcn.com/docs/components/toast */ }
    const { toast } = useToast()

    const [id, setId] = useState(0);
    const [name, setName] = useState("");
    const [position, setPosition] = useState('');
    const [monthlySalary, setMonthlySalary] = useState(0);
    const [etf, setEtf] = useState(0);
    const [otherAllowances, setOtherAllowances] = useState(0);
    const [advancePayments, setAdvancePayments] = useState(0);
    const [loanToPay, setLoanToPay] = useState(0);
    const [loanPaymentForMonth, setLoanPaymentForMonth] = useState(0);
    const [workedDaysCount, setWorkedDaysCount] = useState(0);
    const [shouldWorkDatesTotal, setShouldWorkDatesTotal] = useState(0);
    const [calculatedSalary, setCalculatedSalary] = useState(0);
    const [specialSupports, setSpecialSupports] = useState(0);
    const [other_deductions, setOther_deductions] = useState(0);
    const [month, setMonth] = useState(0);

    // this use to privent number text field value increase and decrease using scroll wheel
    // const disableWheel = (e) => {
    //     e.target.blur();
    // };
    // const disableWheel = (e: WheelEvent<HTMLInputElement>) => {
    //     (e.target as HTMLInputElement).blur();
    // };

    // Format Number using Intl.NumberFormat
    const formatNumber = (num: number): string => {
        return new Intl.NumberFormat('si-LK', { minimumFractionDigits: 2, maximumFractionDigits: 2 }).format(num);
    };

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
        setMonthlySalary(parentData.monthlySalary);
        setEtf(parentData.etf);
        setOtherAllowances(parentData.otherAllowances);
        setAdvancePayments(parentData.advance_payments);
        setLoanToPay(parentData.loan_to_pay);
        setLoanPaymentForMonth(parentData.loan_payment_for_month);
        setWorkedDaysCount(parentData.worked_days_count);
        setShouldWorkDatesTotal(parentData.should_work_dates_total);
        setCalculatedSalary(parentData.calculated_salary)
        setSpecialSupports(parentData.specialSupports);
        setOther_deductions(parentData.other_deductions);
        setMonth(parentData.month);

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
                                        <label className="w-1/3 text-right mr-4 text-gray-700">නම:</label>
                                        <input
                                            type="text"
                                            name="name"
                                            value={name}
                                            onChange={(e) => setName(e.target.value)}
                                            className="w-2/3 px-3 py-1 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                                        />
                                    </div>

                                    <div className="flex items-center">
                                        <label className="w-1/3 text-right mr-4 text-gray-700">මාසය:</label>
                                        <input
                                            type="number"
                                            name="month"
                                            value={month}
                                            min="1"
                                            max="12"
                                            onChange={(e) => setMonth(Number(e.target.value))}
                                            className="w-2/3 px-3 py-1 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                                        />
                                    </div>

                                    <div className="flex items-center">
                                        <label className="w-1/3 text-right mr-4 text-gray-700">තනතුර:</label>
                                        <input
                                            type="text"
                                            name="position"
                                            value={position}
                                            onChange={(e) => setPosition(e.target.value)}
                                            className="w-2/3 px-3 py-1 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                                        />
                                    </div>

                                    <div className="flex items-center">
                                        <label className="w-1/3 text-right mr-4 text-gray-700">මාසික වැටුප:</label>
                                        <input
                                            type="text"
                                            name="monthlySalary"
                                            value={monthlySalary}
                                            onChange={(e) => setMonthlySalary(Number(e.target.value))}
                                            className="w-2/3 px-3 py-1 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                                        />
                                    </div>

                                    <div className="flex items-center">
                                        <label className="w-1/3 text-right mr-4 text-gray-700">අර්ථසාදක:</label>
                                        <input
                                            type="text"
                                            name="etf"
                                            value={etf}
                                            onChange={(e) => setEtf(Number(e.target.value))}
                                            className="w-2/3 px-3 py-1 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                                        />
                                    </div>

                                    <div className="flex items-center">
                                        <label className="w-1/3 text-right mr-4 text-gray-700">වෙනත් දීමනා:</label>
                                        <input
                                            type="text"
                                            name="bonus"
                                            value={otherAllowances}
                                            onChange={(e) => setOtherAllowances(Number(e.target.value))}
                                            className="w-2/3 px-3 py-1 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                                        />
                                    </div>

                                    <div className="flex items-center">
                                        <label className="w-1/3 text-right mr-4 text-gray-700">විශේෂ ආධාර මුදල්:</label>
                                        <input
                                            type="text"
                                            name="specialSupports"
                                            value={specialSupports}
                                            onChange={(e) => setSpecialSupports(Number(e.target.value))}
                                            className="w-2/3 px-3 py-1 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                                        />
                                    </div>

                                    <div className="flex items-center">
                                        <label className="w-1/3 text-right mr-4 text-gray-700">අත්තිකාරම්:</label>
                                        <input
                                            type="text"
                                            name="advance_payments"
                                            value={advancePayments}
                                            onChange={(e) => setAdvancePayments(Number(e.target.value))}
                                            className="w-2/3 px-3 py-1 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                                        />
                                    </div>

                                    <div className="flex items-center">
                                        <label className="w-1/3 text-right mr-4 text-gray-700">වෙනත් අඩු කිරීම්:</label>
                                        <input
                                            type="text"
                                            name="other_deductions"
                                            value={other_deductions}
                                            onChange={(e) => setOther_deductions(Number(e.target.value))}
                                            className="w-2/3 px-3 py-1 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                                        />
                                    </div>

                                    <div className="flex items-center">
                                        <label className="w-1/3 text-right mr-4 text-gray-700">ගෙවිය යුතු මුළු ණය මුදල:</label>
                                        <input
                                            type="text"
                                            name="loan_to_pay"
                                            value={loanToPay}
                                            onChange={(e) => setLoanToPay(Number(e.target.value))}
                                            className="w-2/3 px-3 py-1 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                                        />
                                    </div>

                                    <div className="flex items-center">
                                        <label className="w-1/3 text-right mr-4 text-gray-700">Loan Payment for Month:</label>
                                        <input
                                            type="text"
                                            name="loan_payment_for_month"
                                            value={loanPaymentForMonth}
                                            onChange={(e) => setLoanPaymentForMonth(Number(e.target.value))}
                                            className="w-2/3 px-3 py-1 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                                        />
                                    </div>

                                    <div className="flex items-center">
                                        <label className="w-1/3 text-right mr-4 text-gray-700">පැමිණි දින ගණන:</label>
                                        <input
                                            type="number"
                                            name="worked_days_count"
                                            value={workedDaysCount}
                                            min="1"
                                            max="31"
                                            // onWheel={disableWheel}
                                            onChange={(e) => setWorkedDaysCount(Number(e.target.value))}
                                            className="w-2/3 px-3 py-1 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                                        />
                                    </div>

                                    <div className="flex items-center">
                                        <label className="w-1/3 text-right mr-4 text-gray-700">මුළු දින ගණන:</label>
                                        <input
                                            type="number"
                                            name="should_work_dates_total"
                                            value={shouldWorkDatesTotal}
                                            min="1"
                                            max="31"
                                            // onWheel={disableWheel}
                                            onChange={(e) => setShouldWorkDatesTotal(Number(e.target.value))}
                                            className="w-2/3 px-3 py-1 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                                        />
                                    </div>
                                </div>

                                <div className="ml-5 text-lg font-bold">
                                    <span>ඉතිරි මුදල : {formatNumber(calculatedSalary)} </span>
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
                                    <DeleteEmployee parentData={parentData} />
                                </div>


                                {/* <button type="submit" className="px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2">
                                    Delete Employee
                                </button> */}

                                {/* printer */}
                                <div className="w-full">
                                    <PrintPaySheet empId={id} />
                                </div>


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
