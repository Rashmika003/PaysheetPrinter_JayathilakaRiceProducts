import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs"
import { Printer, UserPlus, Users } from "lucide-react"


const VerticalTabs = () => {
    return (
        <>
            <div className="bg-white w-full h-svh pt-5">
                {/* tabs */}
                <Tabs defaultValue="Add Employees" className="flex flex-row border-2 border-red-500 mx-3 h-full ">
                    <TabsList className="w-1/4 h-full flex flex-col bg-[#314659] justify-start">
                        <div className="bg-zinc-400 h-20 w-full mb-2">
                            user account details
                        </div>
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
                    <div className="w-3/4 ml-5">
                        <TabsContent value="Add Employees">
                            <span className="text-black"> add eployees here</span>
                        </TabsContent>
                        <TabsContent value="printer">
                            <span className="text-black"> printer settings here</span>
                        </TabsContent>
                        <TabsContent value="Employees">
                            <span className="text-black"> all Employees here</span>
                        </TabsContent>
                    </div>

                </Tabs>

            </div>
        </>
    )
}
export default VerticalTabs
