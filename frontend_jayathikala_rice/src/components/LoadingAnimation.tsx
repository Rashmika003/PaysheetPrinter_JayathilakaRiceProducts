import React from 'react';

const LoadingAnimation = () => {
//   const sizeClasses = {
//     sm: 'w-8 h-8',
//     md: 'w-12 h-12',
//     lg: 'w-16 h-16',
//     xl: 'w-24 h-24'
//   };

  return (
    <div className="relative size-32 animate-pulse">
      <div className="absolute inset-0 rounded-full bg-blue-500 opacity-75"></div>
      <div className="absolute inset-0 rounded-full bg-blue-500 opacity-75 animate-ping"></div>
    </div>
  );
};

export default LoadingAnimation;



