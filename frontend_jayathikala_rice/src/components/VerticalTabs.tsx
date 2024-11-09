import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs"
import { Printer, UserPlus, Users } from "lucide-react"
import Employees from "./Employees"
import AddEmployees from "./AddEmployees"


const VerticalTabs = () => {
    return (
        <>
            <div className="bg-white w-full h-svh pt-5">
                {/* tabs */}
                <Tabs defaultValue="Employees" className="flex flex-row mx-3 h-full ">
                    <TabsList className="w-1/4 h-full flex flex-col bg-[#314659] justify-start">
                        {/* <div className="bg-zinc-400 h-20 w-full mb-2">
                            user account details
                        </div> */}
                        <TabsTrigger className="w-full my-1 flex flex-row" value="Employees">
                             <Users /> 
                             <span className="ml-1">Employees</span> 
                        </TabsTrigger>
                        <TabsTrigger className="w-full my-1 flex flex-row" value="Add Employees">
                            <UserPlus />
                            <span className="ml-1">Add Employees</span> 
                        </TabsTrigger>
                        <TabsTrigger className="w-full my-1 flex flex-row" value="printer">
                            <Printer />
                            <span className="ml-1">Printer</span>
                        </TabsTrigger>
                    </TabsList>
                    <div className="w-3/4 max-h-lvh overflow-auto overscroll-contain">
                        <TabsContent value="Add Employees">
                            <AddEmployees />
                        </TabsContent>
                        <TabsContent value="printer">
                            <span className="text-black"> printer settings here. not available </span>
                        </TabsContent>
                        <TabsContent value="Employees">
                            <Employees />
                        </TabsContent>
                    </div>

                </Tabs>

            </div>
        </>
    )
}
export default VerticalTabs
